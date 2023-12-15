import React, { useState } from "react";

export default function RecommendationListItem(props) {
  const [showButtons, setShowButtons] = useState(false)
  const date = new Date(props.recommendation.creationDate);
  const formattedDate = date.toLocaleDateString("fi-FI", {
    year: "numeric",
    month: "2-digit",
    day: "2-digit",
  });

 /* if (props.currUser != null) {
    if (props.currUser.username === props.recommmendation.user.username) {
      setShowButtons(true)
    } else {
      setShowButtons(false)
    }
  } else {
    setShowButtons(false)
  } */

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
        <a className="btn btn-primary btn-xs" href={`/edit/${props.recommendation.id}`}>Edit</a>
      </td>
      <td>
        <button
          className="btn btn-danger"
          onClick={() => props.handleDelete(props.recommendation)}>
            Delete
        </button>
      </td>
    </tr>
  );
}
