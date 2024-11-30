import React from "react";
import { useNavigate } from "react-router-dom";
import notebookPencil from "../assets/cute_notebook_pencil.png";
import "../index.css";

function WelcomePage() {
  const navigate = useNavigate();

  return (
    <div className="welcome-container">
      <h1 className="welcome-text">Welcome to Koray's Todo Lister</h1>
      <p className="welcome-text">There is much to do, so let's start!</p>
      <button onClick={() => navigate("/todolist")} className="start-button">
        Start
      </button>
      <img
        src={notebookPencil}
        alt="Notebook and pencil"
        className="welcome-image"
      />
      <p className="quote">
        "The secret of getting ahead is getting started."
        <span>- Mark Twain</span>
      </p>
    </div>
  );
}

export default WelcomePage;
