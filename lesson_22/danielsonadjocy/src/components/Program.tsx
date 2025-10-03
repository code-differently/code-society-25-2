
export default function Program( title: string, description: string ) {
  return (
    <div className="program">
      <h3>{title}</h3>
      <p>{description}</p>
    </div>
  );
}