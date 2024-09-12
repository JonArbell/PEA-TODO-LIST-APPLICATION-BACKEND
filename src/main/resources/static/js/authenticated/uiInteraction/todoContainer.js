export const createTodoContainer = async (data) => {
    const mainContainer = document.querySelector('#todos-container');
    mainContainer.innerHTML = '';

    console.log('Data : '+data);

    data.forEach(todo => {

        const todoContainer = document.createElement('div');
        todoContainer.classList.add('todo-container');
        todoContainer.id = `${todo.id}`;
        todoContainer.innerHTML = `
                        <h2 id="${todo.id}" onclick="view.viewDetails(${todo.id})">View Details</h2>
        
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
                            <button class="delete-todo-button">Delete</button>
                            <button class="mark-as-done-todo-button">Mark as complete</button>
                            <button class="edit-todo-button">Edit to-do</button>
                        </div>
                        
                        `;

        mainContainer.appendChild(todoContainer);
    });

}