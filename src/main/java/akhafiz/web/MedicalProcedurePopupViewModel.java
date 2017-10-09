package akhafiz.web;

import akhafiz.model.MedicalProcedure;
import akhafiz.services.MedicalProcedureService;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Callback;
import org.zkoss.zul.Window;

import java.util.Collection;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class MedicalProcedurePopupViewModel {

    @Wire
    private Window editMedicalProcedurePopup;

    @WireVariable()
    private MedicalProcedureService medicalProcedureService;

    private MedicalProcedure currentMedicalProcedure;

    public static final String EDITABLE_MEDICAL_PROCEDURE_PARAM_NAME = "editableMedicalProcedure";
    public static final String SAVE_MEDICAL_PROCEDURE_PARAM_NAME = "saveMedicalProcedureCallback";

    @Init
    public void init(@ExecutionArgParam(EDITABLE_MEDICAL_PROCEDURE_PARAM_NAME) MedicalProcedure editableMedicalProcedure) {
        this.currentMedicalProcedure = editableMedicalProcedure != null ? editableMedicalProcedure : new MedicalProcedure();
    }

    @AfterCompose
    public void initSetup(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
    }

    @Command("saveAndClose")
    public void saveAndClose() {
        MedicalProcedure savedMedicalProcedure = medicalProcedureService.addMedicalProcedure(currentMedicalProcedure);
        editMedicalProcedurePopup.detach();
        Collection<Callback> callbacks = editMedicalProcedurePopup.getCallback(SAVE_MEDICAL_PROCEDURE_PARAM_NAME);
        if (callbacks != null) {
            for (Callback callback : callbacks) {
                callback.call(savedMedicalProcedure);
            }

        }

    }

    public MedicalProcedure getCurrentMedicalProcedure() {
        return currentMedicalProcedure;
    }

    public void setCurrentMedicalProcedure(MedicalProcedure currentMedicalProcedure) {
        this.currentMedicalProcedure = currentMedicalProcedure;
    }
}
