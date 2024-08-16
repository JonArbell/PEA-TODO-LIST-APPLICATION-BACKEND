//Functions to handle show Password
function showPasswordHandlerLogin(id,passId){

    const showPasswordCheckBox = document.querySelector(`#${id}`);
    const passwordInput = document.querySelector(`#${passId}`);

    if (!showPasswordCheckBox.hasEventListener) {
        showPasswordCheckBox.addEventListener('click', () => {
            if (passwordInput.type === 'password') {
                passwordInput.type = 'text';
                showPasswordCheckBox.src = '/images/showPassword/open.png';
            } else {
                passwordInput.type = 'password';
                showPasswordCheckBox.src = '/images/showPassword/close.png';
            }
        });
        showPasswordCheckBox.hasEventListener = true; 
    }

}

function onInputPassword(event,id,passId){

    const showPasswordCheckBox = document.querySelector(`#${id}`);

    if(event.target.value){
        showPasswordCheckBox.style.display = 'block';
        showPasswordHandlerLogin(id,passId);
    }else{
        showPasswordCheckBox.style.display = 'none';
    }
    
}

function showModal(containers,modal){

    containers.forEach(container =>{
        container.classList.add('blur-background');
        modal.style.display = 'flex';
    });
    isCreateAccountContainerClicked = true;
}

function closeModal(containers,modal){
    
    containers.forEach(container =>{
        container.classList.remove('blur-background');
        modal.style.display = 'none';
    });
    isCreateAccountContainerClicked = false;
    document.querySelector('#create-new-account-container form').reset();
}


let isCreateAccountContainerClicked = false;

function createAccountContainerHandler(event){
    
    const createAccButton = document.querySelector('#create-new-account-button');
    const allContainers = document.querySelectorAll('.container');
    const modal = document.querySelector('#create-new-account-container');
    const close = document.querySelector('#closeContainer');
    
    if(createAccButton.contains(event.target) || modal.contains(event.target)){

        if(createAccButton.contains(event.target) && isCreateAccountContainerClicked){
            closeModal(allContainers,modal,form);
            return;
        }

        if(close.contains(event.target)){
            closeModal(allContainers,modal);
            return;
        }

        showModal(allContainers,modal);
    
    }else{
        closeModal(allContainers,modal);
    }
    
}

document.addEventListener('click',createAccountContainerHandler);


function createAccountPasswordValidation(event){

    event.preventDefault();

    const inputPassword = document.querySelector('#create-new-password');
    const confirmPassword = document.querySelector('#confirmPassword');
    const createAccountForm = document.querySelector('#create-new-account-container form');

    console.log('Input pass : '+inputPassword.value);
    console.log('Confirm pass : '+confirmPassword.value);

    if(inputPassword.value !== confirmPassword.value){
        alert('Passwords do not match. Please try again.');
        return;
    }else{
        createAccountForm.submit();
    }

}


