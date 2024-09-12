export const createTodoContainer = (data) => {
    const mainContainer = document.querySelector('#todos-container');
    data.forEach(test => {
        console.log('Test lang : '+JSON.stringify(test, null, 2));

        const todoContainer = document.createElement('div');
        todoContainer.classList.add('todo-container');
        todoContainer.id = `${JSON.stringify(test, null, 2).id}`;
        todoContainer.textContent = `${test}`;

        mainContainer.appendChild(todoContainer);
    });


}