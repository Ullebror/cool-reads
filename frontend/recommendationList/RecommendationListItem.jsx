import React, { useEffect, useState } from "react";
import fetchCurrentUser from "./fetchCurrentUser";

export default function RecommendationListItem(props) {
  const [showButtons, setShowButtons] = useState(false);
  const date = new Date(props.recommendation.creationDate);
  const formattedDate = date.toLocaleDateString("fi-FI", {
    year: "numeric",
    month: "2-digit",
    day: "2-digit",
  });

  useEffect(() => {
    if (props.currUser != null) {
      if (props.currUser.id === props.recommendation.user.id) {
        setShowButtons(true);
      } else {
        setShowButtons(false);
      }
    } else {
      setShowButtons(false);
    }
  }, [props.currUser, props.recommendation.user]);

  return (
    <tr key={props.recommendation.id}>
      <td>{props.recommendation.title}</td>
      <td>
        <a href={props.recommendation.link}>{props.recommendation.link}</a>
      </td>
      <td>{props.recommendation.description}</td>
      <td>{formattedDate}</td>
      <td>{props.recommendation.category.name}</td>
      <td>{props.recommendation.user.username}</td>
      <td>
        <div style={{width: "135px"}}>
          {showButtons && (
            <a
              className="btn btn-primary btn-xs"
              href={`/edit/${props.recommendation.id}`}
            >
              Edit
            </a>
          )}
          {showButtons && (
            <button
              style={{ marginLeft: "10px"}}
              className="btn btn-danger"
              onClick={() => props.handleDelete(props.recommendation)}
            >
              Delete
            </button>
          )}
        </div>
      </td>
    </tr>
  );
}
