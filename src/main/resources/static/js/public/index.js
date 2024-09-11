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

        promptMessage.classList.remove('failed-message');
        promptMessage.textContent = '';
        promptMessage.style.display = 'none';
        
        promptMessage.textContent = `Passwords do not match. Please try again.`;
        promptMessage.style.display='flex';
        promptMessage.offsetHeight;
        promptMessage.classList.add('failed-message');        
        
        return false;
    }

    return true;
}

document.addEventListener('DOMContentLoaded',createAccount);

function createAccount(){

    document.querySelector('#create-new-account-container form').addEventListener('submit',(event)=>{

        event.preventDefault();

        const allContainers = document.querySelectorAll('.container');
        const modal = document.querySelector('#create-new-account-container');

        const firstName = document.querySelector('#create-account-firstName').value;
        const lastName = document.querySelector('#create-account-lastName').value;
        const username = document.querySelector('#create-account-username').value;
        const email = document.querySelector('#create-account-email').value;
        const createPassword = document.querySelector('#create-new-password').value;

        fetch('http://localhost:8080/create-account',{
            method : 'POST',
            headers : {
                'Content-Type':'application/json'
            },
            body : JSON.stringify({
                firstName : firstName,
                lastName : lastName,
                username : username,
                email : email,
                password : createPassword
            })
        })
        .then(response => {
            if(!response.ok){
                throw new Error(response);
            }
    
            return response.json();
        })
        .then(data => {
            console.log(data);
        })
        .catch(error =>{
            console.log(error);
            
        });

        closeModal(allContainers,modal);
        
    });
}


function createAccountMessage(createAccMessage){
    const promptMessage = document.querySelector('#prompt-message');

    promptMessage.classList.remove('failed-message');
    promptMessage.classList.remove('success-message');
    promptMessage.style.display='none';
    promptMessage.textContent = '';

    if(createAccMessage != '' || createAccMessage != null){

        // if(createAccMessage == 'success'){
        //     promptMessage.textContent = 'Great job! Your account was created successfully.';
        //     promptMessage.classList.add('success-message');
        //     promptMessage.offsetHeight;
        //     promptMessage.style.display='flex';

        // }else if(createAccMessage.includes('email') || createAccMessage.includes('username') || createAccMessage.length > 7){

        //     promptMessage.textContent = `${createAccMessage}`;
        //     promptMessage.classList.add('failed-message');
        //     promptMessage.offsetHeight;
        //     promptMessage.style.display='flex';

        // }

        console.log('Panis : '+createAccMessage);

        promptMessage.textContent = `${createAccMessage}`;
        promptMessage.classList.add('failed-message');
        promptMessage.offsetHeight;
        promptMessage.style.display='flex';

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
    
    promptMessage.textContent = `${message.error}`;
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
            }),
            credentials: 'include'
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

            console.log(data.token);
            window.location.href = './html/authenticated/main/home.html';
        })
        .catch(error => {
            console.log(error.error);

            handleLoginFailedMessage(error);
            
        });
    });
}

document.addEventListener('DOMContentLoaded',login);


