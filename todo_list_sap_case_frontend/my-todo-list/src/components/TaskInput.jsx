import React, { useState } from 'react';

function TaskInput({ onAddTask }) {
    const [newTask, setNewTask] = useState('');

    const handleKeyPress = (e) => {
        if (e.key === 'Enter') {
            onAddTask(newTask);
            setNewTask('');
        }
    };

    const handleAddClick = () => {
        onAddTask(newTask);
        setNewTask('');
    };

    return (
        <div className="add-task-container">
            <input
                type="text"
                value={newTask}
                onChange={(e) => setNewTask(e.target.value)}
                placeholder="Enter a new task"
                className="add-task-input"
                onKeyDown={handleKeyPress}
            />
            <button onClick={handleAddClick} className="add-task-btn">
                Add Task
            </button>
        </div>
    );
}

export default TaskInput;
