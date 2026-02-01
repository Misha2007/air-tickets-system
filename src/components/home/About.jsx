import { useEffect } from "react";
import "./About.css";

const About = () => {
  return (
    <div id="page">
      <div id="box">
        <div id="name">
          <h1>Flight Ticket System</h1>
        </div>
        <div id="medium-box">
          <div id="mini-box-creators">
            <h2>Creators:</h2>
            <p>Mykhailo Armando Otokar</p>
          </div>
          <div id="mini-box-about">
            <h2>About this site:</h2>
            <p>
              We are a couple of hard working lead miners that make your tesla
              car run. Our everyday life consist of gooning in th mines then
              miming some lead. If we do a good job we get to go mine coal
              instead. All of us have been working here since 1987.
            </p>
          </div>
        </div>
      </div>
    </div>
  );
};
export default About;
