import React from "react";

export default function RecommendationListItem(props) {
  return (
    <tr key={props.recommendation.id}>
        <td>{props.recommendation.title}</td>
        <td><a href={props.recommendation.link}>{props.recommendation.link}</a></td>
        <td>{props.recommendation.description}</td>
        <td>{props.recommendation.creationDate}</td>
        <td>{props.recommendation.category.name}</td>
        <td> <form method="GET" action={`/edit/${props.recommendation.id}`}>
            <button className="btn btn-primary btn-xs">Edit</button>
            </form>
        </td>
        
        <td> <form method="POST" action={`/recommendations/${props.recommendation.id}/delete`}>
            <button className="btn btn-danger">Delete</button>
            </form>
        </td>
    </tr>
  );
}

