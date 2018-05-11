((scope) => {
  class PetList {
    constructor(config) {
      this.listId = config.listId;
      this.editId = config.editId;
      this.deleteId = config.deleteId;
      this.numCheckboxesSelected = 0;
      this.editActive = false;
      this.deleteActive = false;
    }

    register() {
      this.list = document.getElementById(this.listId);
      let checkboxes = this.list.querySelectorAll('tbody input[type=checkbox]');
      for (let checkbox of checkboxes) {
        checkbox.addEventListener('change', (e) => { this.onCheckboxChanged(e); });
      }
    }

    onCheckboxChanged(e) {
      let elem = e.currentTarget;
      if (elem.checked) {
        this.numCheckboxesSelected++;
      } else {
        this.numCheckboxesSelected--;
      }
      if (this.numCheckboxesSelected == 1) {
        this.updateReference();
      } else {
        this.clearReference();
      }
      this.updateDeleteReference();
      if (this.numCheckboxesSelected > 0) {
        this.ensureDeleteOn();
        if (this.numCheckboxesSelected == 1) {
          this.ensureEditOn();
        } else {
          this.ensureEditOff();
        }
      } else {
        this.ensureDeleteOff();
        this.ensureEditOff();
      }
    }

    updateDeleteReference() {
      let trs = this.list.querySelectorAll('tr.is-selected');
      let refs = [];
      for (let tr of trs) {
        let value = tr.dataset.reference;
        refs.push(value);
      }
      let input = document
        .querySelector('input[type=hidden][name=references]');
      input.value = refs.join(',');
    }

    updateReference() {
      let tr = this.list.querySelector('tr.is-selected');
      let value = tr.dataset.reference;
      this.updateReferenceWithValue(value);
    }

    clearReference() {
      this.updateReferenceWithValue('');
    }

    updateReferenceWithValue(value) {
      let input = document
        .querySelector('input[type=hidden][name=reference]');
      input.value = value;
    }

    ensureDeleteOn() {
      if (!this.deleteActive) {
        this.deleteActive = !this.deleteActive;
        this.updateDelete();
      }
    }

    ensureDeleteOff() {
      if (this.deleteActive) {
        this.deleteActive = !this.deleteActive;
        this.updateDelete();
      }
    }

    ensureEditOn() {
      if (!this.editActive) {
        this.editActive = !this.editActive;
        this.updateEdit();
      }
    }

    ensureEditOff() {
      if (this.editActive) {
        this.editActive = !this.editActive;
        this.updateEdit();
      }
    }

    updateDelete() {
      this.getDeleteButton().disabled = !this.deleteActive;
    }

    updateEdit() {
      this.getEditButton().disabled = !this.editActive;
    }

    getEditButton() {
      return document.getElementById(this.editId);
    }

    getDeleteButton() {
      return document.getElementById(this.deleteId);
    }
  }

  scope.app = scope.app || {};
  scope.app.PetList = PetList;
})(window);
