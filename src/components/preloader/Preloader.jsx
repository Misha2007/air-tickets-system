import "./Preloader.css";

const Preloader = () => {
  return (
    <div className="main-container">
      <div className="illuminator-container">
        <div className="illuminator">
          <div className="img"></div>
          <div className="div"></div>
        </div>
      </div>
      <div className="illuminator-container main-picture">
        <div className="illuminator">
          <div className="img"></div>
          <div className="div"></div>
        </div>
      </div>
      <div>
        <div className="illuminator">
          <div className="img"></div>
          <div className="div"></div>
        </div>
      </div>
    </div>
  );
};

export default Preloader;
