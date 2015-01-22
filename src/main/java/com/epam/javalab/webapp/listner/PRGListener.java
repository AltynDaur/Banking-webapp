package com.epam.javalab.webapp.listner;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;


public class PRGListener implements PhaseListener {

    // Init ---------------------------------------------------------------------------------------

    private static final String PRG_DONE_ID = "PostRedirectGetListener.postRedirectGetDone";
    private static final String SAVED_VIEW_ROOT_ID = "PostRedirectGetListener.savedViewRoot";
    private static final String ALL_FACES_MESSAGES_ID = "PostRedirectGetListener.allFacesMessages";


    // Actions ------------------------------------------------------------------------------------

    /**
     * @see javax.faces.event.PhaseListener#getPhaseId()
     */
    public PhaseId getPhaseId() {

        // Only listen during the render response phase.
        return PhaseId.RENDER_RESPONSE;
    }

    /**
     * @see javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
     */
    public void beforePhase(PhaseEvent event) {

        // Prepare.
        FacesContext facesContext = event.getFacesContext();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        Map<String, Object> sessionMap = externalContext.getSessionMap();

        if ("POST".equals(request.getMethod())) {

            // Save viewroot, facesmessages and UIInput values from POST request in session so that
            // they'll be available on the subsequent GET request.
            saveViewRoot(facesContext);
            saveFacesMessages(facesContext);


            // Redirect POST request to GET request.
            redirect(facesContext);

            // Set the PRG toggle.
            sessionMap.put(PRG_DONE_ID, true);

        } else if (sessionMap.containsKey(PRG_DONE_ID)) {

            // Restore any viewroot, facesmessages and UIInput values in the GET request.
            restoreViewRoot(facesContext);
            restoreFacesMessages(facesContext);


            // Remove the PRG toggle.
            sessionMap.remove(PRG_DONE_ID);
        }
    }

    /**
     * @see javax.faces.event.PhaseListener#afterPhase(javax.faces.event.PhaseEvent)
     */
    public void afterPhase(PhaseEvent event) {
        // Do nothing.
    }

    // Helpers ------------------------------------------------------------------------------------

    /**
     * Save the current viewroot of the given facescontext in session. This is important in JSF 1.2,
     * because the viewroot would be lost in the new GET request and will only be created during
     * the afterPhase of RENDER_RESPONSE. But as we need to restore the input values in the
     * beforePhase of RENDER_RESPONSE, we have to save and restore the viewroot first ourselves.
     *
     * @param facesContext The involved facescontext.
     */
    private static void saveViewRoot(FacesContext facesContext) {
        UIViewRoot savedViewRoot = facesContext.getViewRoot();
        facesContext.getExternalContext().getSessionMap()
                .put(SAVED_VIEW_ROOT_ID, savedViewRoot);
    }

    /**
     * Save all facesmessages of the given facescontext in session. This is done so because the
     * facesmessages are purely request scoped and would be lost in the new GET request otherwise.
     *
     * @param facesContext The involved facescontext.
     */
    private static void saveFacesMessages(FacesContext facesContext) {

        // Prepare the facesmessages holder in the sessionmap. The LinkedHashMap has precedence over
        // HashMap, because in a LinkedHashMap the FacesMessages will be kept in order, which can be
        // very useful for certain error and focus handlings. Anyway, it's just your design choice.
        Map<String, List<FacesMessage>> allFacesMessages =
                new LinkedHashMap<String, List<FacesMessage>>();
        facesContext.getExternalContext().getSessionMap()
                .put(ALL_FACES_MESSAGES_ID, allFacesMessages);

        // Get client ID's of all components with facesmessages.
        Iterator<String> clientIdsWithMessages = facesContext.getClientIdsWithMessages();
        while (clientIdsWithMessages.hasNext()) {
            String clientIdWithMessage = clientIdsWithMessages.next();

            // Prepare client-specific facesmessages holder in the main facesmessages holder.
            List<FacesMessage> clientFacesMessages = new ArrayList<FacesMessage>();
            allFacesMessages.put(clientIdWithMessage, clientFacesMessages);

            // Get all messages from client and add them to the client-specific facesmessage list.
            Iterator<FacesMessage> facesMessages = facesContext.getMessages(clientIdWithMessage);
            while (facesMessages.hasNext()) {
                clientFacesMessages.add(facesMessages.next());
            }
        }
    }


    /**
     * Invoke a redirect to the same URL as the current action URL.
     *
     * @param facesContext The involved facescontext.
     */
    private static void redirect(FacesContext facesContext) {

        // Obtain the action URL of the current view.
        String url = facesContext.getApplication().getViewHandler().getActionURL(
                facesContext, facesContext.getViewRoot().getViewId());

        try {
            // Invoke a redirect to the action URL.
            facesContext.getExternalContext().redirect(url);
        } catch (IOException e) {
            // Uhh, something went seriously wrong.
            throw new FacesException("Cannot redirect to " + url + " due to IO exception.", e);
        }
    }

    /**
     * Restore any viewroot from session in the given facescontext.
     *
     * @param facesContext The involved FacesContext.
     */
    private static void restoreViewRoot(FacesContext facesContext) {

        // Remove the saved viewroot from session.
        UIViewRoot savedViewRoot = (UIViewRoot)
                facesContext.getExternalContext().getSessionMap().remove(SAVED_VIEW_ROOT_ID);

        // Restore it in the given facescontext.
        facesContext.setViewRoot(savedViewRoot);
    }

    /**
     * Restore any facesmessages from session in the given FacesContext.
     *
     * @param facesContext The involved FacesContext.
     */
    @SuppressWarnings("unchecked")
    private static void restoreFacesMessages(FacesContext facesContext) {

        // Remove all facesmessages from session.
        Map<String, List<FacesMessage>> allFacesMessages = (Map<String, List<FacesMessage>>)
                facesContext.getExternalContext().getSessionMap().remove(ALL_FACES_MESSAGES_ID);

        // Restore them in the given facescontext.
        for (Map.Entry<String, List<FacesMessage>> entry : allFacesMessages.entrySet()) {
            for (FacesMessage clientFacesMessage : entry.getValue()) {
                facesContext.addMessage(entry.getKey(), clientFacesMessage);
            }
        }
    }


}
