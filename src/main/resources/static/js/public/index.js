//Functions to handle show Password
const openImg = new Image();
openImg.src = './images/showPassword/open.svg';

const closeImg = new Image();
closeImg.src = './images/showPassword/close.svg';

const showPasswordHandlerLogin = (id,passId) =>{

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

const onInputPassword = (event,id,passId) =>{

    const showPasswordCheckBox = document.querySelector(`#${id}`);

    if(event.target.value){
        showPasswordCheckBox.style.display = 'block';
        showPasswordHandlerLogin(id,passId);
    }else{
        showPasswordCheckBox.style.display = 'none';
    }
    
}

const showModal = (containers,modal) =>{

    containers.forEach(container =>{
        container.classList.add('blur-background');
        modal.style.display = 'flex';
    });
    isCreateAccountContainerClicked = true;
}

const closeModal = (containers,modal) =>{
    
    containers.forEach(container =>{
        container.classList.remove('blur-background');
        modal.style.display = 'none';
    });
    isCreateAccountContainerClicked = false;
    document.querySelector('#create-new-account-container form').reset();
}

let isCreateAccountContainerClicked = false;

const createAccountContainerHandler = (event) =>{
    
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

const validateForm = () => {
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

const createAccount = () =>{

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
            if(!response.status !== 201){
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

document.addEventListener('DOMContentLoaded',createAccount);


const createAccountMessage = (createAccMessage) =>{
    const promptMessage = document.querySelector('#prompt-message');

    promptMessage.classList.remove('failed-message');
    promptMessage.classList.remove('success-message');
    promptMessage.style.display='none';
    promptMessage.textContent = '';

    if(createAccMessage != '' || createAccMessage != null){

        console.log('Panis : '+createAccMessage);

        promptMessage.textContent = `${createAccMessage}`;
        promptMessage.classList.add('failed-message');
        promptMessage.offsetHeight;
        promptMessage.style.display='flex';

    }

}


const handleLogoutMessage = (message) =>{
    const promptMessage = document.querySelector('#prompt-message');

    promptMessage.classList.remove('success-message');
    promptMessage.textContent = '';
    promptMessage.style.display='none';

    promptMessage.textContent = `${message}`;
    promptMessage.style.display='flex';
    promptMessage.offsetHeight;
    promptMessage.classList.add('success-message');
    
}

const handleLoginFailedMessage = (message) =>{

    const promptMessage = document.querySelector('#prompt-message');

    promptMessage.classList.remove('failed-message');
    promptMessage.textContent = '';
    promptMessage.style.display = 'none';
    
    promptMessage.textContent = `${message}`;
    promptMessage.style.display='flex';
    promptMessage.offsetHeight;
    promptMessage.classList.add('failed-message');

}

const login = () =>{
    document.querySelector('#login-page form').addEventListener('submit', async (event) =>{

        event.preventDefault();
        
        const usn = document.querySelector('#login-usn').value;
        const pass = document.querySelector('#login-pass').value;

        try{
            const response = await fetch('http://localhost:8080/login',{
                method : 'POST',
                headers : {
                    'Content-Type':'application/json'
                },
                body : JSON.stringify({
                    username : usn,
                    password : pass
                }),
                credentials: 'include'
            });

            if(!response.ok){
                const error = await response.json();
                throw error.error;
            }

            const data = await response.json();
            console.log(data.token);
            window.location.href = './html/authenticated/main/home.html';

        }catch(e){
            console.log(e);
            handleLoginFailedMessage(e);
        }
        
    });
}

document.addEventListener('DOMContentLoaded',login);


