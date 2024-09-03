//Function to display a create new task container
document.addEventListener('click',handleCreateNewItem);
function handleCreateNewItem(event){

    const taskButton = document.querySelector('#create-new-todo');
    const listButton = document.querySelector('#create-new-list');
    const dateTimeProgressContainer = document.querySelector('#date-and-time-progress-display-container');
    const title = document.querySelectorAll('.add-edit-title')[0];
    if(taskButton.contains(event.target)){
        createNewTask(dateTimeProgressContainer,title);
    }else if(listButton.contains(event.target)){
        createNewList();
    }

}
function createNewTask(dateTimeProgressContainer,title){

    if(isPortrait()){
        portraitEditTodo();
    }

    dateTimeProgressContainer.style.display = 'none';
    document.querySelector('#add-edit-task-container').style.display = 'flex';
    document.querySelector(`#add-edit-task-submit`).value ="Save";
    document.querySelector(`#add-edit-task-reset`).value ="Discard";
    title.textContent = 'Add To-do:';
    resetAllInformation();
    handleSubmit();
}


function dim(){
    document.querySelector('#dim-panis').style.display = 'block';
}


function createNewList(){
    document.querySelector('#add-list-container').style.display = 'flex';

    dim();
    resetAllInformation();
    
}


function addHoverCreateNewItemButtons(){
    const taskButton = document.querySelector('#create-new-todo');
    const listButton = document.querySelector('#create-new-list');

    hoverTaskButton(taskButton,listButton);
    removeHoverTaskButton(taskButton,listButton);

}

function hoverTaskButton(taskButton,listButton){
    taskButton.addEventListener('mouseover',()=>{
        taskButton.style.backgroundColor = '#1a2731';
    });

    listButton.addEventListener('mouseover',()=>{
        listButton.style.backgroundColor = '#1a2731';
    });
}

function removeHoverTaskButton(taskButton,listButton){
    taskButton.addEventListener('mouseout',()=>{
        taskButton.style.backgroundColor = '#293b49';
    });

    listButton.addEventListener('mouseout',()=>{
        listButton.style.backgroundColor = '#293b49';
    });
}
document.addEventListener('mousedown',addClickBounceCreateNewTaskListButton);
document.addEventListener('mouseup',()=>{
    document.querySelector('#create-new-todo').style.transform = 'scale(1)';
    document.querySelector('#create-new-list').style.transform = 'scale(1)';
});

function addClickBounceCreateNewTaskListButton(event){
    
    const taskButton = document.querySelector('#create-new-todo');
    const listButton = document.querySelector('#create-new-list');

    if(taskButton.contains(event.target)){
        taskButton.style.backgroundColor = '#1a2731';
        taskButton.style.transform = 'scale(0.95)';
    }else if(listButton.contains(event.target)){
        listButton.style.backgroundColor = '#1a2731';
        listButton.style.transform = 'scale(0.95)';
    }

}