import React from "react";

export default function RecommendationListItem(props) {
  const date = new Date(props.recommendation.creationDate);
  const formattedDate = date.toLocaleDateString("fi-FI", {
    year: "numeric",
    month: "2-digit",
    day: "2-digit",
  });

  return (
    <tr key={props.recommendation.id}>
      <td>{props.recommendation.title}</td>
      <td>
        <a href={props.recommendation.link}>{props.recommendation.link}</a>
      </td>
      <td>{props.recommendation.description}</td>
      <td>{formattedDate}</td>
      <td>{props.recommendation.category.name}</td>
      <td>
        <a className="btn btn-primary btn-xs" href={`/edit/${props.recommendation.id}`}>Edit</a>
      </td>
      <td>
        <button
          className="btn btn-danger"
          onClick={() => props.handleDelete(props.recommendation)}
        >
          Delete
        </button>
      </td>
    </tr>
  );
}
