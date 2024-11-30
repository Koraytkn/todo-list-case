import config from '../config';

const TodoService = {
    fetchTasks: async () => {
        const response = await fetch(`${config.BASE_URL}/todos`);
        if (!response.ok) {
            throw new Error('Failed to fetch tasks');
        }
        return response.json();
    },

    addTask: async (newTask) => {
        const url = `${config.BASE_URL}/todos?task=${encodeURIComponent(newTask.trim())}`;
        const response = await fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
        });

        if (!response.ok) {
            throw new Error('Failed to add task');
        }
    },

    updateTaskStatus: async (taskId, isCompleted) => {
        const endpoint = isCompleted ? 'complete' : 'incomplete';
        const response = await fetch(`${config.BASE_URL}/todos/${taskId}/${endpoint}`, { method: 'PUT' });

        if (!response.ok) {
            throw new Error(`Failed to mark task as ${endpoint}`);
        }
    },

    deleteTask: async (taskId) => {
        const response = await fetch(`${config.BASE_URL}/todos/${taskId}`, { method: 'DELETE' });

        if (!response.ok) {
            throw new Error('Failed to delete task');
        }
    },
};

export default TodoService;
