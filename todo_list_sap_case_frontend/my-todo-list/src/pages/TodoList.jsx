import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import TaskList from '../components/TaskList';
import TaskInput from '../components/TaskInput';
import notebookPencil from '../assets/cute_notebook_pencil.png';
import TodoService from '../services/TodoService';

function TodoList() {
    const navigate = useNavigate();
    const [tasks, setTasks] = useState([]);
    const [message, setMessage] = useState('');
    const [showMessage, setShowMessage] = useState(false);

    const fetchTasks = async () => {
        try {
            const data = await TodoService.fetchTasks();
            setTasks(data);
        } catch (error) {
            console.error('Error fetching tasks:', error);
        }
    };

    const displayMessage = (msg) => {
        setMessage(msg);
        setShowMessage(true);
        setTimeout(() => {
            setShowMessage(false);
        }, 5000); // 5 seconds
    };

    const handleAddTask = async (newTask) => {
        if (newTask.trim() === '') return;

        try {
            await TodoService.addTask(newTask);
            await fetchTasks();
            displayMessage('New task added successfully!');
        } catch (error) {
            console.error('Error adding task:', error);
            displayMessage('Failed to add task');
        }
    };

    const handleCheckboxChange = async (taskId, isCompleted) => {
        try {
            await TodoService.updateTaskStatus(taskId, isCompleted);
            await fetchTasks();
            displayMessage(`Task successfully marked as ${isCompleted ? 'complete' : 'incomplete'}!`);
        } catch (error) {
            console.error('Error updating task status:', error);
            displayMessage('Failed to update task status');
        }
    };

    const handleDelete = async (taskId) => {
        try {
            await TodoService.deleteTask(taskId);
            await fetchTasks();
            displayMessage('Task successfully deleted!');
        } catch (error) {
            console.error('Error deleting task:', error);
            displayMessage('Failed to delete task');
        }
    };

    useEffect(() => {
        fetchTasks();
    }, []);

    return (
        <div>
            <div className="todo-header">
                <img
                    src={notebookPencil}
                    alt="Notebook and pencil"
                    className="todo-header-image left"
                />
                <h2>Your To-Do List</h2>
                <img
                    src={notebookPencil}
                    alt="Notebook and pencil"
                    className="todo-header-image right"
                />
            </div>
            <div className="todo-list-container">
                <TaskList tasks={tasks} handleCheckboxChange={handleCheckboxChange} handleDelete={handleDelete} />
            </div>
            <div className={`message ${showMessage ? 'visible' : 'hidden'}`}>
                {message && <p>{message}</p>}
            </div>
            <TaskInput onAddTask={handleAddTask} />
            <button onClick={() => navigate('/')} className="back-button">
                Back to Welcome Page
            </button>
        </div>
    );
}

export default TodoList;
