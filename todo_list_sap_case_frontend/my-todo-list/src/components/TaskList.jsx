import React from 'react';
import { FaTrashAlt } from 'react-icons/fa';

function TaskList({ tasks, handleCheckboxChange, handleDelete }) {
    return (
        <div className="task-list-container">
            <table className="task-list-table">
                <thead>
                    <tr>
                        <th>Index</th>
                        <th>Description</th>
                        <th>Completed</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    {tasks.map((task, index) => (
                        <tr key={task.id}>
                            <td>{index + 1}</td> {/* The current index rather than the task ID coming from the BE */}
                            <td>{task.task}</td>
                            <td>
                                <input
                                    type="checkbox"
                                    checked={task.completed}
                                    onChange={() => handleCheckboxChange(task.id, !task.completed)}
                                />
                            </td>
                            <td>
                                <button onClick={() => handleDelete(task.id)} className="delete-btn">
                                    <FaTrashAlt />
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default TaskList;
