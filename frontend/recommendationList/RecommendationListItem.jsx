import React from "react";

export default function RecommendationListItem(props) {
  const date = new Date(props.recommendation.creationDate);
  const formattedDate = date.toLocaleDateString('fi-FI', {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit',
  });

  return (
    <tr key={props.recommendation.id}>
        <td>{props.recommendation.title}</td>
        <td><a href={props.recommendation.link}>{props.recommendation.link}</a></td>
        <td>{props.recommendation.description}</td>
        <td>{formattedDate}</td>
        <td>{props.recommendation.category.name}</td>
        <td> <form method="GET" action={`/edit/${props.recommendation.id}`}>
            <button className="btn btn-primary btn-xs">Edit</button>
            </form>
        </td>
        
        <td> <form method="GET" action={`/delete/${props.recommendation.id}`}>
            <button className="btn btn-danger">Delete</button>
            </form>
        </td>
    </tr>
  );
}

