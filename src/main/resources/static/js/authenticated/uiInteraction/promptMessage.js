export const failedMessage = async (message) => {

    const promptMessage = document.querySelector('#prompt-message');

    promptMessage.classList.remove('bg-[rgb(127, 180, 127)]','animate-fadeOut','bg-[rgb(255, 69, 58)]');
    promptMessage.textContent = '';
    promptMessage.style.display = 'none';
    
    promptMessage.textContent = `${message}`;
    promptMessage.style.display='flex';
    promptMessage.offsetHeight;
    promptMessage.classList.add('bg-[rgb(255, 69, 58)]','animate-fadeOut');

}

export const successMessage = async (message) => {

    const promptMessage = document.querySelector('#prompt-message');

    promptMessage.classList.remove('bg-[rgb(127, 180, 127)]','animate-fadeOut','bg-[rgb(255, 69, 58)]');
    promptMessage.textContent = '';
    promptMessage.style.display = 'none';
    
    promptMessage.textContent = `${message}`;
    promptMessage.style.display='flex';
    promptMessage.offsetHeight;
    promptMessage.classList.add('bg-[rgb(127, 180, 127)]','animate-fadeOut');

}