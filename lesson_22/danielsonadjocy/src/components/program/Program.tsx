
export default function Program( {title, description} : {title: string, description: string} ) {
  return (
    <>
      <h3>{title}</h3>
      <p>{description}</p>
    </>
  );
}