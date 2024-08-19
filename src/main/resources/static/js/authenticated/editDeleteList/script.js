
function clickedDelete(){
    document.querySelector('#delete-list-container-modal').style.display ='flex';
    document.querySelector('#delete-select-list-container').style.display ='block';
    removeModal(document.querySelector('#edit-delete-list-container-modal'));
}


function deleteList(){
    const listSelected = document.querySelector('#listSelectDelete');
    const listName = listSelected.options[listSelected.selectedIndex].dataset.name;

    const confirm = document.querySelector('#delete-form-container h2');

    confirm.textContent =`Are you sure you want to delete ${listName}? If checked, all tasks within this list will also be deleted. This action cannot be undone.`;

    document.querySelector('#delete-select-list-container').style.display ='none';

    document.querySelector('#delete-form-container').style.display = 'block';

}


function deleteListSubmit(){
    const form = document.querySelector('#delete-form-container form');

    const listSelected = document.querySelector('#listSelectDelete');
    form.action = `/pea/list/delete/${listSelected.value}`;

    const check = document.querySelector('#check-all-tasks').checked;
    form.querySelector('input').value = check;
    
}


function deleteListDiscard(){

}
