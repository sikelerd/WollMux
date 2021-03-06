package de.muenchen.allg.itd51.wollmux.dialog;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.star.awt.XButton;
import com.sun.star.awt.XControl;
import com.sun.star.awt.XControlContainer;
import com.sun.star.awt.XTextComponent;
import com.sun.star.awt.XWindow;
import com.sun.star.uno.Exception;
import com.sun.star.uno.UnoRuntime;

import de.muenchen.allg.afid.UNO;
import de.muenchen.allg.itd51.wollmux.core.db.ColumnNotFoundException;
import de.muenchen.allg.itd51.wollmux.core.db.LocalOverrideStorageStandardImpl.LOSDJDataset;

public class DatensatzBearbeitenOrgaWizardPage extends DatensatzBearbeitenBaseWizardPage
{
  private static final Logger LOGGER = LoggerFactory
      .getLogger(DatensatzBearbeitenOrgaWizardPage.class);

  public DatensatzBearbeitenOrgaWizardPage(XWindow parentWindow, short pageId, LOSDJDataset dataset,
      List<String> dbSchema) throws Exception
  {
    super(pageId, parentWindow, "DatensatzBearbeitenOrga", dataset, dbSchema);

    try
    {
      XControlContainer controlContainerOrga = UnoRuntime.queryInterface(XControlContainer.class,
          window);
      setControlContainer(controlContainerOrga);

      // wenn ldap-datensatz unterschiedlich zu datensatz aus LOS (cache.conf), textfarbe = rot.
      for (String columnName : dbSchema)
      {
        XControl xControl = controlContainerOrga.getControl(columnName);

        if (xControl == null)
          continue;

        XTextComponent xTextComponent = UNO.XTextComponent(xControl);

        if (xTextComponent == null)
          continue;

        if (dataset.isDifferentFromLdapDataset(columnName, dataset))
        {
          showAcceptLdapValueButton(columnName, true);
          setTextColor(xControl, 16711680); // rot
        }

        xTextComponent.setText(dataset.get(columnName) == null ? "" : dataset.get(columnName));
      }

      for (XControl control : controlContainerOrga.getControls())
      {
        XTextComponent textComponent = UNO.XTextComponent(control);

        XButton xButton = UNO.XButton(control);

        if (textComponent != null)
          textComponent.addTextListener(textListener);

        if (xButton != null)
          xButton.addActionListener(buttonActionListener);
      }
    } catch (ColumnNotFoundException e)
    {
      LOGGER.error("", e);
    }
  }

}
