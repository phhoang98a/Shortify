import './App.css';
import Layout from './components/layout';
import Shortener from './components/shortener';

function App() {
  return (
    <div className="App">
      <Layout>
        <Shortener/>
      </Layout>
    </div>
  );
}

export default App;
