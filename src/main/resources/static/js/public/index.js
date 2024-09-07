//Functions to handle show Password
const openImg = new Image();
openImg.src = './images/showPassword/open.svg';

const closeImg = new Image();
closeImg.src = './images/showPassword/close.svg';

function showPasswordHandlerLogin(id,passId){

    const showPasswordCheckBox = document.querySelector(`#${id}`);
    const passwordInput = document.querySelector(`#${passId}`);


    if (!showPasswordCheckBox.hasEventListener) {
        showPasswordCheckBox.addEventListener('click', () => {
            if (passwordInput.type === 'password') {
                showPasswordCheckBox.src = openImg.src;
                passwordInput.type = 'text';
                
            } else {
                showPasswordCheckBox.src = closeImg.src;
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
    
    }
    
}

document.addEventListener('click',createAccountContainerHandler);

function validateForm() {
    const password = document.getElementById('create-new-password');
    const confirmPassword = document.getElementById('confirmPassword');
    const promptMessage = document.getElementById('prompt-message');


    if (password.value !== confirmPassword.value) {

        promptMessage.textContent = 'Passwords do not match. Please try again.';
        promptMessage.classList.add('failed-message');
        promptMessage.style.display='flex';
        
        setTimeout(()=>{
            promptMessage.textContent = '';
            promptMessage.classList.remove('failed-message');
            promptMessage.style.display='none';
        },7000);

        return false;
    }

    return true; 
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


function handleLogoutMessage(message){
    const promptMessage = document.querySelector('#prompt-message');

    promptMessage.classList.remove('success-message');
    promptMessage.textContent = '';
    promptMessage.style.display='none';

    promptMessage.textContent = `${message}`;
    promptMessage.style.display='flex';
    promptMessage.offsetHeight;
    promptMessage.classList.add('success-message');
    
}

function handleLoginFailedMessage(message){

    const promptMessage = document.querySelector('#prompt-message');

    promptMessage.classList.remove('failed-message');
    promptMessage.textContent = '';
    promptMessage.style.display = 'none';
    
    promptMessage.textContent = `${(message.Error) ? message.Error : (message.password) ? message.password : message.username}`;
    promptMessage.style.display='flex';
    promptMessage.offsetHeight;
    promptMessage.classList.add('failed-message');

}

function login(){
    document.querySelector('#login-page form').addEventListener('submit',function(event){

        event.preventDefault();
        
        const usn = document.querySelector('#login-usn').value;
        const pass = document.querySelector('#login-pass').value;
        
        fetch('http://localhost:8080/login',{
            method : 'POST',
            headers : {
                'Content-Type':'application/json'
            },
            body : JSON.stringify({
                username : usn,
                password : pass
            })
        })
        .then(response => {

            if(!response.ok){
                return response.json().then(errorData => {
                    throw errorData; 
                });
            }
            return response.json();

        })
        .then(data => {

            sessionStorage.setItem("jwtAuthToken",data.token);

            console.log(data.token);
            window.location.href = './lopit.html';
        })
        .catch(error => {
            console.log(error);

            handleLoginFailedMessage(error);
            
        });
    });
}

document.addEventListener('DOMContentLoaded',login);


