
function clickedDelete(){

    const mainContainer = document.querySelector('#delete-list-container-modal');
    const deleteSelect = document.querySelector('#delete-select-list-container');

    mainContainer.style.display = 'flex';
    deleteSelect.style.display = 'block';
    isDeleteClicked = true;
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


function deleteEditListDiscard(){

    const listSelected = document.querySelector('#listSelectDelete');
    const listName = listSelected.options[listSelected.selectedIndex].dataset.name;

    listName.value = '';

    const confirm = document.querySelector('#delete-form-container h2');

    confirm.textContent ='';

    document.querySelector('#delete-form-container').style.display = 'none';
    document.querySelector('#delete-list-container-modal').style.display = 'none';
    document.querySelector('#edit-list-container-modal').style.display = 'none';
}

function clickedEdit(event){

    const container = document.querySelector('#edit-list-container-modal');
    const main = document.querySelector('#edit-delete-list-container-modal');
    const mainButton = document.querySelector('#edit-button-list-container-modal');

    if(mainButton.contains(event.target)){
        showEditListContainer(container,main);

    }

}

function showEditListContainer(container,main){
    container.style.display = 'flex';
    main.style.display = 'none';
}


