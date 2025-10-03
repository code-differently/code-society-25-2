
export default function Program( {title, description} : {title: string, description: string} ) {
  return (
    <div className="program">
      <h3>{title}</h3>
      <p>{description}</p>
    </div>
  );
}