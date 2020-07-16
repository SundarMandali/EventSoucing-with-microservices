import React from "react";

import {
  BrowserRouter as Router,
  Link,
  Route,
  Redirect,
} from "react-router-dom";
import axios from "axios";

export default class Get extends React.Component {
  constructor() {
    super();
    this.state = {
      id: 0,
      events: [],
      pastevents: [],
      load: false,
      load1: false,
    };
    this.submit = this.submit.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  componentDidMount() {
    axios({
      url: "http://localhost:8085/orderhistory/orderdetails",
      method: "get",
    })
      .then((res) => {
        this.setState({ events: res.data, load: true });
        console.log(res.data);
      })
      .catch((err) => window.alert(err.message));
  }
  handleChange(event) {
    const { name, value } = event.target;
    this.setState({
      id: value,
    });
  }

  submit(e) {
    e.preventDefault();

    axios({
      url: "http://localhost:8085/orderhistory/listevnts/" + this.state.id,
      method: "get",
    })
      .then((res) => {
        console.log(res.data);
        this.setState({
          pastevents: res.data,
          load1: true,
        });
      })
      .catch((err) => window.alert(err.message));
  }
  render() {
    return (
      <div>
        <br />
        <br />
        <h4>The details of the your order is</h4>
        <hr />
        <br />
        <div>
          {this.state.load && (
            <div>
              <h5>Orderid: {this.state.events.orderid}</h5>
              <h5>flavour: {this.state.events.flavour}</h5>
              <h5>quantity: {this.state.events.quantity}</h5>
              <h5>Order-Status: {this.state.events.status}</h5>
            </div>
          )}
        </div>
        <hr />
        <br />
        <br />
        Enter the id and submit to get the details of the history of order
        <br />
        <br />
        <input
          type="textbox"
          value={this.state.id}
          onChange={this.handleChange}
        ></input>
        <br />
        <br />
        <button onClick={this.submit}>Get Details of the past order</button>
        <br />
        <br />
        <div>
          {this.state.load1 &&
            this.state.pastevents.map((value, key) => {
              return (
                <div key={key}>
                  <h5>
                    The event name is{" "}
                    <h4 style={{ color: "red", display: "inline" }}>
                      {value.event}
                    </h4>{" "}
                    happened at{" "}
                    <h5 style={{ color: "green", display: "inline" }}>
                      {value.time}
                    </h5>
                  </h5>
                </div>
              );
            })}
        </div>
        <Link to="/">Go to Main page</Link>
      </div>
    );
  }
}
