import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';
import { Link } from 'react-router-dom';

class Create extends Component {

    constructor() {
        super();
        this.state = {
            name: '',
            description: '',
            state: '',
        };
    }
    onChange = (e) => {
        const state = this.state
        state[e.target.name] = e.target.value;
        this.setState(state);
    }

    onSubmit = (e) => {
        e.preventDefault();

        const { name, description, state} = this.state;

        axios.post('http://localhost:9090/project', { name, description, state})
            .then((result) => {
                this.props.history.push("/")
            });
    }

    render() {
        const { name, description, state} = this.state;
        return (
            <div className="container">
                <div className="panel panel-default">
                    <div className="panel-heading">
                        <h3 className="panel-title">
                            ADD PROJECT
                        </h3>
                    </div>
                    <div className="panel-body">
                        <h4><Link to="/"><span className="glyphicon glyphicon-th-list" aria-hidden="true"></span> Project List</Link></h4>
                        <form onSubmit={this.onSubmit}>
                            <div className="form-group">
                                <label htmlFor="isbn">Name:</label>
                                <input type="text" className="form-control" name="name" value={name} onChange={this.onChange} placeholder="Name" />
                            </div>
                            <div className="form-group">
                                <label htmlFor="title">Description:</label>
                                <input type="text" className="form-control" name="description" value={description} onChange={this.onChange} placeholder="Description" />
                            </div>
                            <div className="form-group">
                                <label htmlFor="author">State:</label>
                                <input type="boolean" className="form-control" name="state" value={state} onChange={this.onChange} placeholder="state" />
                            </div>
                            {/*<div class="form-group">
                                <label for="published_date">Postal Code:</label>
                                <input type="number" class="form-control" name="postalCode" value={postalCode} onChange={this.onChange} placeholder="Postal Code" />
                            </div>
                            <div class="form-group">
                                <label for="publisher">Phone:</label>
                                <input type="tetx" class="form-control" name="phone" value={phone} onChange={this.onChange} placeholder="Phone Number" />
                            </div>*/}
                            <button type="submit" className="btn btn-default">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        );
    }
}

export default Create;