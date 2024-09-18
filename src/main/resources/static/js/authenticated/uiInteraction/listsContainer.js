export const createListsContainer = async (data) => {

    const displayListContainer = document.querySelector('#lists-container > ul');
    displayListContainer.innerHTML = '';

    const editListNameSelect = document.querySelector('#edit-list-name-container > form > select');
    editListNameSelect.innerHTML = '';

    const deleteListSelect = document.querySelector('#delete-select-list-container > select');
    deleteListSelect.innerHTML = '';

    const addTodoListContainer = document.querySelector('#add-edit-todo-list');
    if(addTodoListContainer){
        addTodoListContainer.innerHTML = '';

        const none = document.createElement('option');
        none.textContent = "None";
        none.value = "0";
        addTodoListContainer.appendChild(none);
    }

    data.forEach(list =>{

        if(list.listName !== 'Personal' && list.listName !== 'Work'){
            const editListNameOption = document.createElement('option');
            editListNameOption.textContent = `${list.listName}`;
            editListNameOption.value = `${list.id}`;
            editListNameSelect.appendChild(editListNameOption);

            const deleteListOption = document.createElement('option');
            deleteListOption.textContent = `${list.listName}`;
            deleteListOption.value = `${list.id}`;
            deleteListSelect.appendChild(deleteListOption);

        }

        const li = document.createElement('li');
        li.innerHTML = `
            <a href="/list/${list.id}">
                <p>${list.listName}</p>
            </a>
            
        `;
        displayListContainer.appendChild(li);

        if(addTodoListContainer){
            const option = document.createElement('option');
            option.textContent = `${list.listName}`;
            option.value = `${list.id}`;
            addTodoListContainer.appendChild(option);
        }

    });

}