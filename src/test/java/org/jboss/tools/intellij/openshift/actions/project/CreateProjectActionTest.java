package org.jboss.tools.intellij.openshift.actions.project;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jboss.tools.intellij.openshift.tree.application.ApplicationNode;
import org.jboss.tools.intellij.openshift.tree.application.ApplicationsRootNode;
import org.jboss.tools.intellij.openshift.tree.application.ComponentNode;
import org.jboss.tools.intellij.openshift.tree.application.PersistentVolumeClaimNode;
import org.jboss.tools.intellij.openshift.tree.application.ProjectNode;
import org.jboss.tools.intellij.openshift.tree.application.ServiceNode;
import org.jboss.tools.intellij.openshift.actions.ActionTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateProjectActionTest extends ActionTest {
  @Override
  public AnAction getAction() {
    return new CreateProjectAction();
  }

  public void testActionOnLoggedInCluster() {
    ApplicationsRootNode applicationsRootNode = mock(ApplicationsRootNode.class);
    when(applicationsRootNode.isLogged()).thenReturn(true);
    AnActionEvent event = createEvent(applicationsRootNode);
    AnAction action = getAction();
    action.update(event);
    assertTrue(event.getPresentation().isVisible());
  }

  public void testThatActionOnLoggedOutCluster() {
    ApplicationsRootNode applicationsRootNode = mock(ApplicationsRootNode.class);
    when(applicationsRootNode.isLogged()).thenReturn(false);
    AnActionEvent event = createEvent(applicationsRootNode);
    AnAction action = getAction();
    action.update(event);
    assertFalse(event.getPresentation().isVisible());
  }

  public void testActionOnProject() {
    ProjectNode projectNode = mock(ProjectNode.class);
    AnActionEvent event = createEvent(projectNode);
    AnAction action = getAction();
    action.update(event);
    assertFalse(event.getPresentation().isVisible());
  }

  public void testActionOnApplication() {
    ApplicationNode applicationNode = mock(ApplicationNode.class);
    AnActionEvent event = createEvent(applicationNode);
    AnAction action = getAction();
    action.update(event);
    assertFalse(event.getPresentation().isVisible());
  }

  public void testActionIsDisabledOnComponent() {
    ComponentNode componentNode = mock(ComponentNode.class);
    AnActionEvent event = createEvent(componentNode);
    AnAction action = getAction();
    action.update(event);
    assertFalse(event.getPresentation().isVisible());
  }

  public void testActionOnService() {
    ServiceNode serviceNode = mock(ServiceNode.class);
    AnActionEvent event = createEvent(serviceNode);
    AnAction action = getAction();
    action.update(event);
    assertFalse(event.getPresentation().isVisible());
  }


  public void testActionOnStorage() {
    PersistentVolumeClaimNode storageNode = mock(PersistentVolumeClaimNode.class);
    AnActionEvent event = createEvent(storageNode);
    AnAction action = getAction();
    action.update(event);
    assertFalse(event.getPresentation().isVisible());
  }

}