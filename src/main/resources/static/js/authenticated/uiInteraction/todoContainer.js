export const createTodoContainer = async (todos) => {
    
    if(window.location.pathname !== '/about-us' && window.location.pathname !== '/contact-us' && window.location.pathname !== '/settings'){
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

        document.querySelector('#no-tasks-display').style.display = 'none';
        document.querySelector('#todos-container').classList.remove('hidden');

        const unorderedList = document.createElement('ul');
        unorderedList.classList.add('grid','xl:grid-cols-3','md:gap-x-2','lg:gap-x-4','gap-y-4','py-4','md:grid-cols-2','xs:h-[73vh]','lg:h-auto', 'lg:max-h-[70vh]','overflow-y-scroll','xs:scrollbar-hide','lg:scrollbar-visible');
        
        todos.forEach(todo => {

            const list = document.createElement('li');
            list.classList.add('xs:w-[95%]','md:w-full','justify-self-center');

            const todoContainer = document.createElement('div');
            todoContainer.classList.add('border-2','border-solid','border-green-color','rounded-2xl','flex','flex-col','items-center','justify-evenly','h-25vh');
            
            todoContainer.innerHTML = `
                            <h2 id="${todo.id}" class="flex items-center justify-center h-[2rem] cursor-pointer text-green-color font-bold underline xs:text-base lg:text-xl w-[50%] m-4" onclick="ViewDetails.viewDetails(${todo.id})" role="button">View Details</h2>
            
                            <div class="flex items-center xs:justify-between md:justify-center h-10 w-[90%]">
                                <h3 class="font-bold text-center w-1/3 xs:text-xs lg:text-sm">Title</h3>
                                <h3 class="font-bold text-center w-1/3 xs:hidden md:block xs:text-xs lg:text-sm">Completed</h3>
                                <h3 class="font-bold text-center w-1/3 xs:text-xs lg:text-sm">Target Date</h3>
                            </div>
        
                            <hr class="border-t-2 border-green-color w-[90%] mx-auto"/>
            
                            <div class="flex items-center xs:justify-between md:justify-center h-10 w-[90%]">
                                <p class="text-center w-1/3 xs:text-xs lg:text-sm">${todo.title}</p>
                                <p class="text-center w-1/3 xs:hidden md:block xs:text-xs lg:text-sm">${todo.done? 'Yes' : 'No'}</p>
                                <p class="text-center w-1/3 xs:text-xs lg:text-sm">${todo.formattedDate}</p>
                            </div>
        
                            <hr class="border-t-2 border-green-color w-[90%] mx-auto"/>
            
                            <div class="flex items-center justify-evenly w-[90%]">

                                <button class="delete-todo-button xs:text-[7px] lg:text-[9px] 2xl:text-xs text-white-color bg-[#400707] w-[25%] p-2 rounded-xl" onclick="CrudTodoUi.deleteTodoUi(${todo.id})">Delete</button>

                                <button class="mark-as-done-todo-button xs:text-[7px] lg:text-[9px] 2xl:text-xs bg-[rgb(162,157,157)] w-1/3 p-2 rounded-xl" onclick="CrudTodoUi.todoMarkAsDoneUi(${todo.id})">Mark as complete</button>

                                <button class="edit-todo-button xs:text-[7px] lg:text-[9px] 2xl:text-xs text-white-color bg-green-bg w-[25%] p-2 rounded-xl" onclick="CrudTodoUi.editTodoUi(${todo.id})">Edit to-do</button>

                            </div>
                            
                            `;
    
            list.appendChild(todoContainer);
            unorderedList.appendChild(list);
            
        });

        mainContainer.appendChild(unorderedList);
    
    }
    
}