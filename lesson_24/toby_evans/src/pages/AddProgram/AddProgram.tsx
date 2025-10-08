import './AddProgram.scss';

export default function AddProgram() {
  console.log('✅ AddProgram component is rendering...');

  return (
    <section className="add-program" style={{padding: '2rem'}}>
      <h2>Add a New Program</h2>
      <form>
        <input type="text" placeholder="Program title" />
        <textarea placeholder="Program description" />
        <button>Add Program</button>
      </form>
    </section>
  );
}
