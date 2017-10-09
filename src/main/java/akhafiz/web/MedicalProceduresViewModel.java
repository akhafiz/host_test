package akhafiz.web;

import akhafiz.model.MedicalProcedure;
import akhafiz.services.MedicalProcedureService;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Callback;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class MedicalProceduresViewModel {

    @Wire
    private Window medicalProceduresWindow;

    @Wire
    private Listbox medicalProcedureListBox;

    @WireVariable()
    private MedicalProcedureService medicalProcedureService;

    private List<MedicalProcedure> medicalProcedureList = new ArrayList<>();

    private MedicalProcedure selectedMedicalProcedure;

    @Init
    public void init() {
        medicalProcedureList.addAll(medicalProcedureService.getAllMedicalProcedures());
    }

    @AfterCompose
    public void initSetup(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
    }

    @Command("showCreateMedicalProcedurePopup")
    public void showCreateMedicalProcedurePopup() {
        openEditMedicalProcedurePopup(null);
    }

    @Command("showEditMedicalProcedurePopup")
    public void showEditMedicalProcedurePopup() {

        if (selectedMedicalProcedure != null) {
            openEditMedicalProcedurePopup(selectedMedicalProcedure);
        }
    }

    private void openEditMedicalProcedurePopup(MedicalProcedure editableMedicalProcedure) {
        Window w = (Window) Executions.createComponents(
                "WEB-INF/views/editMedicalProcedurePopup.zul",
                null,
                editableMedicalProcedure != null ?
                        Collections.singletonMap(
                                MedicalProcedurePopupViewModel.EDITABLE_MEDICAL_PROCEDURE_PARAM_NAME,
                                selectedMedicalProcedure) : null);
        w.addCallback(MedicalProcedurePopupViewModel.SAVE_MEDICAL_PROCEDURE_PARAM_NAME , new Callback() {
            @Override
            public void call(Object data) {
                medicalProcedureList = medicalProcedureService.getAllMedicalProcedures();
                medicalProcedureListBox.setModel(new ListModelList<>(medicalProcedureList));
            }
        });
        w.doModal();
    }

    @Command("deleteMedicalProcedure")
    public void deleteMedicalProcedure() {
        if (selectedMedicalProcedure != null) {
            medicalProcedureService.deleteMedicalProcedure(selectedMedicalProcedure);
            medicalProcedureList.remove(selectedMedicalProcedure);
            selectedMedicalProcedure = null;
            medicalProcedureListBox.setModel(new ListModelList<>(medicalProcedureList));
        }
    }

    public List<MedicalProcedure> getMedicalProcedureList() {
        return medicalProcedureList;
    }

    public MedicalProcedure getSelectedMedicalProcedure() {
        return selectedMedicalProcedure;
    }

    public void setSelectedMedicalProcedure(MedicalProcedure selected) {
        this.selectedMedicalProcedure = selected;
    }
}
