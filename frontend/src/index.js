import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import Home from './pages/Home';
import { BrowserRouter, Route, Switch } from 'react-router-dom';

ReactDOM.render(
  <BrowserRouter>
    <Switch>
      <Route path='/' component={Home} exact/>
      <Route component={() => (<div>404</div>)}/>
    </Switch>
  </BrowserRouter>,
  document.getElementById('root')
);