//These functions is for showing create new item modal
document.addEventListener('mouseup',createNewHandle);
document.addEventListener('mousedown',addClickBounceCreateNewbutton);
let isCreateNewButtonClicked = false;
function createNewHandle(event){
    const createNewButton = document.querySelector('#create-new-button');
    const container = document.querySelector('#create-new-modal-container');
    const taskButton = document.querySelector('#create-new-todo');
    const listButton = document.querySelector('#create-new-list');

    createNewButton.style.backgroundColor = '#293b49';
    createNewButton.style.transform = 'scale(1)';

    if(createNewButton.contains(event.target) || container.contains(event.target)){

        if(container.contains(event.target)){

            if(taskButton.contains(event.target) || listButton.contains(event.target)){
                closeCreateNewModalContainer(container);
                return;
            }

            showCreateNewModalContainer(container);
            return;
        }

        if(isCreateNewButtonClicked){
 
            closeCreateNewModalContainer(container);
            return;
        }
        
        showCreateNewModalContainer(container);
    }else{
        
        closeCreateNewModalContainer(container);
    }
    
}
addHoverCreateNewButton();
function addHoverCreateNewButton(){
    const createNewButton = document.querySelector('#create-new-button');

    createNewButton.addEventListener('mouseover',(event)=>{
        if(createNewButton.contains(event.target)){
            createNewButton.style.backgroundColor = '#1a2731';
        }
    });

    createNewButton.addEventListener('mouseout',(event)=>{
        if(createNewButton.contains(event.target)){
            createNewButton.style.backgroundColor = '#293b49';
        }
    });

}
function addClickBounceCreateNewbutton(event){
    
    const createNewButton = document.querySelector('#create-new-button');

    if(createNewButton.contains(event.target)){
        createNewButton.style.backgroundColor = '#1a2731';
        createNewButton.style.transform = 'scale(0.95)';
    }

}

function showCreateNewModalContainer(container){
    container.style.display = 'flex';
    isCreateNewButtonClicked = true;
    addHoverCreateNewItemButtons();
}

function closeCreateNewModalContainer(container){
    container.style.display = 'none';
    isCreateNewButtonClicked = false;
}