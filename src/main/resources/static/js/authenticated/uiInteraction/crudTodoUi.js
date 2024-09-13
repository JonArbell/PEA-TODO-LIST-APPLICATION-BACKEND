import {Todo} from '../index.js';

export const deleteTodoUi = async (id) =>{
    await Todo.deleteTodo(id);
}

export const panis = () =>{
    console.log('Lopit');
}