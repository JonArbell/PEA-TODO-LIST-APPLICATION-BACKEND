export const createTodoContainer = async (data) => {
    const mainContainer = document.querySelector('#todos-container');
    
    if(data.length === 0){
        document.querySelector('#main-head').style.display = 'none';
        document.querySelector('#todos-container').style.display = 'none';
        return;
    }else{
        document.querySelector('#main-head').style.display = 'flex';
        document.querySelector('#todos-container').style.display = 'grid';
        document.querySelector('#no-tasks-display').style.display = 'none';
    }
    mainContainer.innerHTML = '';
    data.forEach(todo => {

        const todoContainer = document.createElement('div');
        todoContainer.classList.add('todo-container');
        todoContainer.id = `${todo.id}`;
        todoContainer.innerHTML = `
                        <h2 id="${todo.id}" onclick="ViewDetails.viewDetails(${todo.id})">View Details</h2>
        
                        <div class="todo-container-header">
                            <h3>Title</h3>
                            <h3>Completed</h3>
                            <h3>Target Date</h3>
                        </div>
    
                        <hr/>
        
                        <div class="todo-container-data">
                            <p class="title">${todo.title}</p>
                            <p class="completed">${todo.done? 'Yes' : 'No'}</p>
                            <p class="target-date">${todo.formattedDate}</p>
                        </div>
    
                        <hr/>
        
                        <div>
                            <button class="delete-todo-button" onclick="CrudTodoUi.deleteTodoUi(${todo.id})">Delete</button>
                            <button class="mark-as-done-todo-button" onclick="CrudTodoUi.todoMarkAsDoneUi(${todo.id})">Mark as complete</button>
                            <button class="edit-todo-button" onclick="CrudTodoUi.editTodoUi(${todo.id})">Edit to-do</button>
                        </div>
                        
                        `;

        mainContainer.appendChild(todoContainer);
    });

}