import React, { Component } from 'react';

import {BrowserRouter as Router, Link, Route} from 'react-router-dom';
import Main from './Main';
import Get from './Get';

class App extends React.Component{

  render(){
    return(
      <Router>
      <Route exact path="/" component={Main}/>
      <Route path="/get" component={Get}/>
      </Router>
    )
  }
  

}

export default App;
