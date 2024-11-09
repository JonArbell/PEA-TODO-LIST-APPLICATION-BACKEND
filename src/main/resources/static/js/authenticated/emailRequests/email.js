export const feedback = async (event)  =>{

    event.preventDefault();

    const fullName = document.querySelector('#feedback-full-name').value;
    const email = document.querySelector('#feedback-email').value;
    const message = document.querySelector('#feedback-message').value;

    const ui = document.querySelector('#user-interface').checked;
    const functionality = document.querySelector('#functionality').checked;
    const performance = document.querySelector('#performance').checked;
    const ux = document.querySelector('#user-experience').checked;
    const other = document.querySelector('#other').value;

    const prod = 'https://pea-todo-list-application.onrender.com';
    const dev = 'http://localhost:8080';

    try{

        const csrfToken = document.querySelector('meta[name="_csrf_authenticated"]').content;

        const url = `${prod}/api/authenticated/send-feedback`;

        const response = await fetch(url, {
            method: 'POST',
            credentials: 'include',
            body: JSON.stringify({
                email: email,
                fullName: fullName,
                message: message,
                subject: 'PEA User feedback',
                userInterfaceCheck: ui,
                functionalityCheck: functionality,
                performanceCheck: performance,
                userExperienceCheck: ux,
                other: other
            }),
            headers: {
                'X-XSRF-TOKEN': csrfToken,
                'Content-Type': 'application/json',
            }
        });
        
        console.log(response);
        
    }catch(error){

    }

}