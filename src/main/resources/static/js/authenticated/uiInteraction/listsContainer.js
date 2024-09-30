export const createListsContainer = async (data) => {

    const displayListContainer = document.querySelector('#lists-container > nav > ul');
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
                <p class="p-4 pl-6 hover:bg-white-bg hover:text-green-color">${list.listName}</p>
            </a>
        `;
        displayListContainer.appendChild(li);

        if(addTodoListContainer){
            const option = document.createElement('option');
            option.textContent = `${list.listName}`;
            option.value = `${list.id}`;
            addTodoListContainer.appendChild(option);
        }

        if(window.location.pathname === `/list/${list.id}`){
            document.querySelector('title').textContent = `P E A | List - ${list.listName}`;
            document.querySelector('#head-title > h2').textContent = `${list.listName} Tasks :`;
        }

    });

    if(data.length === 2){

        const deleteListOption = document.createElement('option');
        deleteListOption.textContent = 'None';
        deleteListOption.value = 0;
        deleteListSelect.appendChild(deleteListOption);
    }

}