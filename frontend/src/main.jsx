import React from "react";
import ReactDOM from "react-dom";

import { createStore, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import {Provider, connect} from "react-redux";

import QuoteBox from './Components/QuoteBox.jsx';
import { reducer, quoteAction } from './redux/actionAndReducer.js';

import 'bootstrap/dist/css/bootstrap.min.css'

import './index.css';

const store = createStore(reducer, applyMiddleware(thunk))

const mapStateToProps = (state) => {
    return {quote: state}
};

const mapDispatchToProps = (dispatch) => {
    return {
        dispatching: () => {
            dispatch(quoteAction())
        }
    }
};

const Container = connect(mapStateToProps, mapDispatchToProps)(QuoteBox);

ReactDOM.render(
    <Provider store={store}>
        <Container/>
    </Provider>, 
    document.querySelector('#root')
);