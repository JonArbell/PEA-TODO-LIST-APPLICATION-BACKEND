export const createTodoContainer = async (todos) => {
    
    if(window.location.pathname !== '/about-us' && window.location.pathname !== '/contact-us'){
        const mainContainer = document.querySelector('#todos-container');
        mainContainer.innerHTML = '';
        
        const totalTasks = document.querySelector('#total-tasks');
        totalTasks.innerHTML = '';

        totalTasks.textContent = `${todos.length}`;
        if(todos.length === 0){
            document.querySelector('#no-tasks-display').style.display = 'block';
            mainContainer.innerHTML = '';
            document.querySelector('#todos-container').classList.add('hidden');
            return;
        }

        mainContainer.innerHTML = `
            <div class="grid grid-cols-4 text-center m-4 mt-2 mb-2 font-bold">
                <h2>Title</h2>
                <h2>Completed</h2>
                <h2>Target Date</h2>
                <div></div>
            </div>
        `;

        document.querySelector('#no-tasks-display').style.display = 'none';
        document.querySelector('#todos-container').classList.remove('hidden');
        
        todos.forEach((todo,index) => {

            const todoContainer = document.createElement('div');
            const bgColor = `${(index%2) == 0 ? 'bg-white-color' : 'bg-white-bg'}`;
            const border = `${(index%2) == 0 ? 'border-2' : index === (todos.length-1) ? 'border-b-2' : 'border-b-0'}`; 
            const lastItemBorder = `${index === (todos.length-1) ? 'border-t-0' : 'none'}`;
            todoContainer.classList.add(
                `${bgColor}`,
                'grid',
                'grid-cols-4',
                'text-center',
                'items-center',
                'p-4',
                'px-0',
                `${border}`,
                'border-r-0',
                'border-l-0',
                `${lastItemBorder}`,
                'border-solid',
                'border-green-color'
            );
            todoContainer.id = `${todo.id}`;
            todoContainer.innerHTML = `
                <p>${todo.title}</p>
                <p>${todo.done? 'Yes' : 'No'}</p>
                <p>${todo.formattedDate}</p>
                <div class="flex justify-around">

                    <h3 id="${todo.id}" role="button" class="underline cursor-pointer text-green-color" onclick="ViewDetails.viewDetails(${todo.id})">View Details</h3>
            
                    <button>
                        <img alt="Edit button" class="h-4 w-4" src="../../../images/edit_button.png" onclick="CrudTodoUi.editTodoUi(${todo.id})"/>
                    </button>

                    <button>
                        <img alt="Delete button" class="h-4 w-4" src="../../../images/delete_button.png" onclick="CrudTodoUi.deleteTodoUi(${todo.id})"/>
                    </button>
                </div>
            `;
            // todoContainer.innerHTML = `
            //                 <h2 id="${todo.id}" class="flex items-center justify-center h-[2rem] cursor-pointer text-green-color font-bold underline text-2xl w-[50%] m-4" onclick="ViewDetails.viewDetails(${todo.id})" role="button">View Details</h2>
            
            //                 <div class="flex items-center justify-center w-[90%]">
            //                     <h3 class="${classes} font-bold">Title</h3>
            //                     <h3 class="${classes} font-bold">Completed</h3>
            //                     <h3 class="${classes} font-bold">Target Date</h3>
            //                 </div>
        
            //                 <hr class="border-t-2 border-green-color w-[90%] mx-auto"/>
            
            //                 <div class="flex items-center justify-center w-[90%]">
            //                     <p class="${classes}">${todo.title}</p>
            //                     <p class="${classes}">${todo.done? 'Yes' : 'No'}</p>
            //                     <p class="${classes}">${todo.formattedDate}</p>
            //                 </div>
        
            //                 <hr class="border-t-2 border-green-color w-[90%] mx-auto"/>
            
            //                 <div class="flex items-center justify-center w-[90%]">

            //                     <button class="delete-todo-button ${buttonClasses} text-sm text-white-color bg-[#400707]" onclick="CrudTodoUi.deleteTodoUi(${todo.id})">Delete</button>

            //                     <button class="mark-as-done-todo-button ${buttonClasses} text-sm bg-[rgb(162, 157, 157)]" onclick="CrudTodoUi.todoMarkAsDoneUi(${todo.id})">Mark as complete</button>

            //                     <button class="edit-todo-button ${buttonClasses} text-sm text-white-color bg-green-bg" onclick="CrudTodoUi.editTodoUi(${todo.id})">Edit to-do</button>

            //                 </div>
                            
            //                 `;
    
            mainContainer.appendChild(todoContainer);
        });
    
    }
    
}