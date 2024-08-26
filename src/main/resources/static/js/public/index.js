//Functions to handle show Password
function showPasswordHandlerLogin(id,passId){

    const showPasswordCheckBox = document.querySelector(`#${id}`);
    const passwordInput = document.querySelector(`#${passId}`);

    if (!showPasswordCheckBox.hasEventListener) {
        showPasswordCheckBox.addEventListener('click', () => {
            if (passwordInput.type === 'password') {
                showPasswordCheckBox.src = '/images/showPassword/open.png';
                passwordInput.type = 'text';
                
            } else {
                showPasswordCheckBox.src = '/images/showPassword/close.png';
                passwordInput.type = 'password';
                
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


function createAccountMessage(createAccMessage){
    const promptMessage = document.querySelector('#prompt-message');

    if(createAccMessage != '' || createAccMessage != null){

        if(createAccMessage == 'success'){
            promptMessage.textContent = 'Great job! Your account was created successfully.';
            promptMessage.classList.add('success-message');
            promptMessage.style.display='flex';

            setTimeout(()=>{
                promptMessage.classList.remove('success-message');
                promptMessage.textContent = '';
            },7000);

        }else if(createAccMessage.includes('email') || createAccMessage.includes('username') || createAccMessage.length > 7){

            promptMessage.textContent = `${createAccMessage}`;
            promptMessage.classList.add('failed-message');
            promptMessage.style.display='flex';
            setTimeout(()=>{
                promptMessage.classList.remove('failed-message');
                promptMessage.textContent = '';
            },7000);
        }

        

    }

    

}