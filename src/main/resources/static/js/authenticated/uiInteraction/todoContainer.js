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
                'w-full',
                'border-4',
                'border-solid',
                'border-green-color',
                'rounded-2xl',
                'flex',
                'flex-col',
                'gap-2',
                'h-30vh'
            );
            todoContainer.id = `${todo.id}`;
            const classes = 'h-[3rem] flex items-center justify-center w-[30%]';
            const buttonClasses = `${classes} border-2 border-solid border-black rounded-2xl`;
            
            todoContainer.innerHTML = `
                            <h2 id="${todo.id}" class="flex items-center justify-center h-[2rem] cursor-pointer text-green-color font-bold underline text-2xl w-[50%] m-4" onclick="ViewDetails.viewDetails(${todo.id})" role="button">View Details</h2>
            
                            <div class="flex items-center justify-center w-[90%]">
                                <h3 class="${classes} font-bold">Title</h3>
                                <h3 class="${classes} font-bold">Completed</h3>
                                <h3 class="${classes} font-bold">Target Date</h3>
                            </div>
        
                            <hr class="border-t-2 border-green-color w-[90%] mx-auto"/>
            
                            <div class="flex items-center justify-center w-[90%]">
                                <p class="${classes}">${todo.title}</p>
                                <p class="${classes}">${todo.done? 'Yes' : 'No'}</p>
                                <p class="${classes}">${todo.formattedDate}</p>
                            </div>
        
                            <hr class="border-t-2 border-green-color w-[90%] mx-auto"/>
            
                            <div class="flex items-center justify-center w-[90%]">

                                <button class="delete-todo-button ${buttonClasses} text-sm text-white-color bg-[#400707]" onclick="CrudTodoUi.deleteTodoUi(${todo.id})">Delete</button>

                                <button class="mark-as-done-todo-button ${buttonClasses} text-sm bg-[rgb(162, 157, 157)]" onclick="CrudTodoUi.todoMarkAsDoneUi(${todo.id})">Mark as complete</button>

                                <button class="edit-todo-button ${buttonClasses} text-sm text-white-color bg-green-bg" onclick="CrudTodoUi.editTodoUi(${todo.id})">Edit to-do</button>

                            </div>
                            
                            `;
    
            mainContainer.appendChild(todoContainer);
        });
    
    }
    
}