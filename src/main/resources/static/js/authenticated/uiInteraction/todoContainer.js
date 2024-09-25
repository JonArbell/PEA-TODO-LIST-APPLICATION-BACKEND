export const createTodoContainer = async (todos) => {
    
    if(window.location.pathname !== '/about-us' && window.location.pathname !== '/contact-us'){
        const mainContainer = document.querySelector('#todos-container');
        mainContainer.innerHTML = '';
    
        const totalTasks = document.querySelector('#total-tasks');
        totalTasks.innerHTML = '';

        totalTasks.textContent = `${todos.length}`;
        if(todos.length === 0){
            document.querySelector('#no-tasks-display').style.display = 'block';
            return;
        }
        document.querySelector('#no-tasks-display').style.display = 'none';

        todos.forEach(todo => {

            const todoContainer = document.createElement('div');
            todoContainer.classList.add(
                'border-2',
                'border-solid',
                'border-green-color',
                'rounded-2xl',
                'flex',
                'flex-col',
                'w-[25vw]',
                'h-30vh'
            );
            todoContainer.id = `${todo.id}`;
            todoContainer.innerHTML = `
                            <h2 id="${todo.id}" class="text-center cursor-pointer text-green-color font-bold" onclick="ViewDetails.viewDetails(${todo.id})" role="button">View Details</h2>
            
                            <div class="flex justify-center w-[90%]">
                                <h3 class="h-[10vh]">Title</h3>
                                <h3 class="h-[10vh]">Completed</h3>
                                <h3 class="h-[10vh]">Target Date</h3>
                            </div>
        
                            <hr class="bg-green-color h-2"/>
            
                            <div class="flex justify-center w-[90%]">
                                <p class="h-[10vh]">${todo.title}</p>
                                <p class="h-[10vh]">${todo.done? 'Yes' : 'No'}</p>
                                <p class="h-[10vh]">${todo.formattedDate}</p>
                            </div>
        
                            <hr/>
            
                            <div class="flex justify-center h-1/4">
                                <button class="delete-todo-button" onclick="CrudTodoUi.deleteTodoUi(${todo.id})">Delete</button>
                                <button class="mark-as-done-todo-button" onclick="CrudTodoUi.todoMarkAsDoneUi(${todo.id})">Mark as complete</button>
                                <button class="edit-todo-button" onclick="CrudTodoUi.editTodoUi(${todo.id})">Edit to-do</button>
                            </div>
                            
                            `;
    
            mainContainer.appendChild(todoContainer);
        });
    
    }
    
}