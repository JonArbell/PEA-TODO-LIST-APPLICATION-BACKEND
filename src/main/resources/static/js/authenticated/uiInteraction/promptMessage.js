export const failedMessage = async (message) => {

    const promptMessage = document.querySelector('#prompt-message');

    promptMessage.classList.remove('bg-[rgb(127,180,127)]','bg-[rgb(255,69,58)]');
    promptMessage.textContent = '';
    promptMessage.style.display = 'none';
    
    promptMessage.textContent = `${message}`;
    promptMessage.style.display='flex';
    promptMessage.offsetHeight;
    promptMessage.classList.add('bg-[rgb(255,69,58)]');

    setTimeout(()=>{

        promptMessage.style.display='none';

    },7050);

}

export const successMessage = async (message) => {

    const promptMessage = document.querySelector('#prompt-message');

    promptMessage.classList.remove('bg-[rgb(127,180,127)]','bg-[rgb(255,69,58)]');
    promptMessage.textContent = '';
    promptMessage.style.display = 'none';
    
    promptMessage.textContent = `${message}`;
    promptMessage.style.display='flex';
    promptMessage.offsetHeight;
    promptMessage.classList.add('bg-[rgb(127,180,127)]');

    setTimeout(()=>{

        promptMessage.style.display='none';

    },7050);

}