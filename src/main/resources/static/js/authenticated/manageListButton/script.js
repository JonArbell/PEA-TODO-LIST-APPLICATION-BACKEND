
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

document.addEventListener('mouseover',manageListHover);

function manageListHover(event){
    const button = document.querySelector('#manage-list-container button');


    if(button.contains(event.target)){
        manageListHovered(button);
    }else{
        manageListNotclicked(button);
    }

}
document.addEventListener('mouseout',manageListRemoveHovered);

function manageListRemoveHovered(event){

    const button = document.querySelector('#manage-list-container button');

    if(button.contains(event.target)){
        manageListNotHovered(button);
    }

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