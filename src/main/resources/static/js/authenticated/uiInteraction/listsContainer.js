export const createListsContainer = async (data) => {

    const displayListContainer = document.querySelector('#lists-container > ul');
    displayListContainer.innerHTML = '';

    const editListSelect = document.querySelector('#edit-list-id');

    const addTodoListContainer = document.querySelector('#add-edit-todo-list');
    addTodoListContainer.innerHTML = '';
    const none = document.createElement('option');
    none.textContent = "None";
    none.value = "0";
    addTodoListContainer.appendChild(none);

    data.forEach(list =>{

        const li = document.createElement('li');
        li.innerHTML = `
            <p>${list.listName}</p>
        `;
        displayListContainer.appendChild(li);

        const option = document.createElement('option');
        option.textContent = `${list.listName}`;
        option.value = `${list.id}`;
        addTodoListContainer.appendChild(option);

        const editListOption = document.createElement('option');
        editListOption.textContent = `${list.listName}`;
        editListOption.value = `${list.id}`;
        editListSelect.appendChild(editListOption);

    });

}