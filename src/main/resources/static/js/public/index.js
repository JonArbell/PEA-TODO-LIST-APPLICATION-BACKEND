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

    document.querySelector('#create-new-account-container form').addEventListener('submit', async (event)=>{

        event.preventDefault();

        const allContainers = document.querySelectorAll('.container');
        const modal = document.querySelector('#create-new-account-container');

        const firstName = document.querySelector('#create-account-firstName').value;
        const lastName = document.querySelector('#create-account-lastName').value;
        const username = document.querySelector('#create-account-username').value;
        const email = document.querySelector('#create-account-email').value;
        const createPassword = document.querySelector('#create-new-password').value;

        const csrfToken = document.querySelector('meta[name="_csrf"]').content;

        try{

            const prod = 'https://pea-todo-list-application.onrender.com/api/create-account';
            const dev = 'http://localhost:8080/api/create-account';

            const response = await fetch(prod,{
                method : 'POST',
                headers : {
                    'Content-Type':'application/json',
                    'X-XSRF-TOKEN': csrfToken 
                },
                body : JSON.stringify({
                    firstName : firstName,
                    lastName : lastName,
                    username : username,
                    email : email,
                    password : createPassword
                })
            });

            if(response.status !== 201){
                const error = await response.json();
                throw error.createUserError;
            }

            const message = await response.json();

            createAccountSuccessMessage(message.message);

            closeModal(allContainers,modal);

        }catch(e){
            createAccountFailedMessage(e);
        }

    });
}

document.addEventListener('DOMContentLoaded',createAccount);

const createAccountFailedMessage = (createAccMessage) =>{
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

const createAccountSuccessMessage = (createAccMessage) =>{
    const promptMessage = document.querySelector('#prompt-message');

    promptMessage.classList.remove('failed-message');
    promptMessage.classList.remove('success-message');
    promptMessage.style.display='none';
    promptMessage.textContent = '';

    if(createAccMessage != '' || createAccMessage != null){

        console.log('Panis : '+createAccMessage);

        promptMessage.textContent = `${createAccMessage}`;
        promptMessage.classList.add('success-message');
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
        const csrfToken = document.querySelector('meta[name="_csrf"]').content;
        
        try{

            const prod = 'https://pea-todo-list-application.onrender.com';
            const dev = 'http://localhost:8080';

            const url = `${prod}/api/login`;

            const response = await fetch(url,{
                method : 'POST',
                headers : {
                    'Content-Type': 'application/json',
                    'X-XSRF-TOKEN': csrfToken 
                },
                body : JSON.stringify({
                    username : usn,
                    password : pass
                }),
                credentials: 'include'
            });

            if(!response.ok){
                const error = await response.json();
                throw error.loginError;
            }

            window.location.href = `${prod}/home`;
        }catch(e){
            console.log(e);
            handleLoginFailedMessage(e);
        }
        
    });
}

document.addEventListener('DOMContentLoaded',login);

