//This is for show Manage list Modal
document.addEventListener('mouseup',showManageListModal);
let isClicked = false;
function showManageListModal(event){
    const button = document.querySelector('#manage-list-container button');
    const container = document.querySelector('#edit-delete-list-container-modal');

    if(button.contains(event.target) || container.contains(event.target)){

        if(container.contains(event.target)){
            return;
        }

        if(isClicked){
            removeModal(container);
            return;
        }

        showModal(container);
        manageListNotclicked(button);
    }else{
        removeModal(container);
    }

}


function showModal(container){
    container.style.display = 'block';
    isClicked = true;
}

function removeModal(container){
    container.style.display = 'none';
    isClicked = false;
}

document.addEventListener('mousedown',addClickBounceManageListButton);

function addClickBounceManageListButton(event){

    const button = document.querySelector('#manage-list-container button');

    if(button.contains(event.target)){

        manageListClicked(button);

    }

}

manageListHover();
function manageListHover(){
    const button = document.querySelector('#manage-list-container button');


    button.addEventListener('mouseover',(event)=>{
        if(button.contains(event.target)){
            manageListHovered(button);
        }
    });

    button.addEventListener('mouseout',(event)=>{
        if(button.contains(event.target)){
            manageListNotHovered(button);
            manageListNotclicked(button);
        }
    });

}

function manageListHovered(button){

    button.style.backgroundColor = '#254336';
    button.style.color = '#ece6d0';

}

function manageListNotHovered(button){
    button.style.backgroundColor = '#d0cab5';
    button.style.color = '#254336';
}


function manageListClicked(button){

    button.style.transform = 'scale(0.95)';
    button.style.filter = 'brightness(85%)';
}

function manageListNotclicked(button){
    button.style.transform = '';
    button.style.filter = '';
}


//This is for buttons in manage list modal
addHoverEditListName();
function addHoverEditListName(){
    const button = document.querySelector('#edit-delete-list-container-modal div button');

    button.addEventListener('mouseover',(event)=>{
        if(button.contains(event.target)){
            editListNameHovered(button);
        }
    });

    button.addEventListener('mouseout',(event)=>{
        if(button.contains(event.target)){
            editListNameNotHovered(button);
            
        }
    });

}

function editListNameHovered(button){
    button.style.backgroundColor = '#19232c';
}
function editListNameNotHovered(button){
    button.style.backgroundColor = '#293b49';
}



addHoverDeleteList();
function addHoverDeleteList(){
    const button = document.querySelector('#delete-button-list-container-modal');

    button.addEventListener('mouseover',(event)=>{
        if(button.contains(event.target)){
            deleteListHovered(button);
        }
    });

    button.addEventListener('mouseout',(event)=>{
        if(button.contains(event.target)){
            deleteListNotHovered(button);
            
        }
    });

}

function deleteListHovered(button){
    button.style.backgroundColor = 'rgb(29, 0, 0)';
}
function deleteListNotHovered(button){
    button.style.backgroundColor = 'rgb(56, 0, 0)';
}