//These functions is for profile modal
document.addEventListener('mouseup',clickProfile);
let isProfileClicked = false;
function clickProfile(event){
    const logo = document.querySelector('#profile-logo');
    logo.style.transform = 'scale(1)';
    logo.style.filter = 'brightness(100%)';
    const container = document.querySelector('#profile-modal');
    if(logo.contains(event.target) || container.contains(event.target)){

        
        if(container.contains(event.target)){
            openProfileModalContainer(container);
            return;
        }

        if(isProfileClicked){
            closeProfileModalContainer(container);
            return;
        }

        openProfileModalContainer(container);
    }else{
        closeProfileModalContainer(container);
    }

}

function openProfileModalContainer(container){

    const editListContainer = document.querySelector('#add-edit-list-container');

    if(editListContainer !== null){
        if(editListContainer.style.display != 'flex'){
            container.style.display = 'flex';
            isProfileClicked = true;
        }
    }else{
        container.style.display = 'flex';
        isProfileClicked = true;
    }
}

function closeProfileModalContainer(container){
    container.style.display = 'none';
    isProfileClicked = false;
}

document.addEventListener('mousedown',addBounceClickProfile);
function addBounceClickProfile(event){
    const logo = document.querySelector('#profile-logo');

    if(logo.contains(event.target)){

        logo.style.transform = 'scale(0.95)';
        logo.style.filter = 'brightness(70%)';
    }

}

addHoverLogoProfile();
function addHoverLogoProfile(){
    const logo = document.querySelector('#profile-logo');

    logo.addEventListener('mouseover',(event)=>{
        if(logo.contains(event.target)){
            logo.style.filter = 'brightness(85%)';
        }
    });

    logo.addEventListener('mouseout',(event)=>{
        if(logo.contains(event.target)){
            logo.style.transform = 'scale(1)';
            logo.style.filter = 'brightness(100%)';
        }
    });

}